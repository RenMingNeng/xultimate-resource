package org.danielli.xultimate.context.format.support;

import java.util.Map;

import org.antlr.stringtemplate.StringTemplate;
import org.danielli.xultimate.context.format.FormatException;
import org.danielli.xultimate.context.format.Formatter;
import org.danielli.xultimate.ui.stringtemplate.v3.StringTemplateUtils;

/**
 * 格式化器。是StringTemplate 3.x版本的实现。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 * @see Formatter
 */
public class StringTemplateV3Formatter implements Formatter<String, Map<String, ? extends Object>, String> {

	@Override
	public String format(String source, Map<String, ? extends Object> parameter) throws FormatException {
		StringTemplate template = new StringTemplate(source);
		return StringTemplateUtils.processTemplateIntoString(template, parameter);
	}

}
