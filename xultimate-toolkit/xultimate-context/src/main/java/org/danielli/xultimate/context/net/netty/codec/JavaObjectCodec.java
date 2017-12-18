package org.danielli.xultimate.context.net.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.io.Serializable;
import java.util.List;

import org.danielli.xultimate.core.io.support.JavaObjectInput;
import org.danielli.xultimate.core.io.support.JavaObjectOutput;

/**
 * 通过{@code ObjectSerializer}提供的功能完成序列化/解序列化支持。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
@Sharable
public class JavaObjectCodec extends ChannelHandlerAdapter {
	
	protected int bufferSize = 256;
	
	public JavaObjectCodec() {

	}
	
	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}
	
	private final MessageToMessageEncoder<Serializable> encoder = new MessageToMessageEncoder<Serializable>() {

        @Override
        protected void encode(ChannelHandlerContext ctx, Serializable msg, List<Object> out) throws Exception {
        	JavaObjectCodec.this.encode(ctx, msg, out);
        }
    };

    private final MessageToMessageDecoder<ByteBuf> decoder = new MessageToMessageDecoder<ByteBuf>() {

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        	JavaObjectCodec.this.decode(ctx, msg, out);
        }
    };
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        decoder.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        encoder.write(ctx, msg, promise);
    }
    
    protected void encode(ChannelHandlerContext ctx, Serializable msg, List<Object> out)  throws Exception {
    	JavaObjectOutput javaObjectOutput = new JavaObjectOutput(bufferSize) ;
    	try {
    		javaObjectOutput.writeObject(msg);
    		byte[] result = javaObjectOutput.toBytes();
    		out.add(Unpooled.wrappedBuffer(result));
    	} finally {
    		javaObjectOutput.close();
    	}
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
    	JavaObjectInput javaObjectInput = new JavaObjectInput(new ByteBufInputStream(msg), bufferSize);
    	try {
    		while (javaObjectInput.available() > 0) {
    			out.add(javaObjectInput.readObject());
        	}
    	} finally {
    		javaObjectInput.close();
    	}
    }
}
