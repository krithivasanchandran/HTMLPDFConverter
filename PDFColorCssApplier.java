package com.billing.paypal.pdf;

import java.util.HashMap;
import java.util.Map;

import com.itextpdf.html2pdf.attach.ITagWorker;
import com.itextpdf.html2pdf.attach.ProcessorContext;
import com.itextpdf.html2pdf.css.CssConstants;
import com.itextpdf.html2pdf.css.apply.impl.BlockCssApplier;
import com.itextpdf.styledxmlparser.node.IStylesContainer;

public class HWColorCssApplier extends BlockCssApplier {

	public static final Map<String, String> KLEUR = new HashMap<String, String>();

	static {
		KLEUR.put("odd","red");
		KLEUR.put("even","green");
	}

	@Override
	public void apply(ProcessorContext context, IStylesContainer stylesContainer, ITagWorker tagWorker) {

		Map<String, String> cssStyles = stylesContainer.getStyles();
		
		cssStyles.keySet().forEach((t)-> {
			System.out.println("cssstyles  :: -> " + t);
		});
        
		if (cssStyles.containsKey(CssConstants.BACKGROUND_COLOR)) {
			cssStyles.put(CssConstants.BACKGROUND, KLEUR.get("odd"));
			stylesContainer.setStyles(cssStyles);
		}
		if (cssStyles.containsKey(CssConstants.BACKGROUND_COLOR)) {
			cssStyles.put(CssConstants.BACKGROUND, KLEUR.get("even"));
			stylesContainer.setStyles(cssStyles);
		}
		super.apply(context, stylesContainer, tagWorker);
	}
}