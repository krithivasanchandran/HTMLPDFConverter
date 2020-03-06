package com.billing.paypal.pdf;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfArray;
import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfNumber;
import com.itextpdf.kernel.pdf.PdfPage;

public class ScaleDownEventHandler implements IEventHandler{
	
	protected float scale = 1;
    protected PdfDictionary pageDict;

    public ScaleDownEventHandler(float scale) {
        this.scale = scale;
    }

    public void setPageDict(PdfDictionary pageDict) {
        this.pageDict = pageDict;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfPage page = docEvent.getPage();
        page.put(PdfName.Rotate, pageDict.getAsNumber(PdfName.Rotate));

        scaleDown(page, pageDict, PdfName.MediaBox, scale);
        scaleDown(page, pageDict, PdfName.CropBox, scale);
    }

    protected void scaleDown(PdfPage destPage, PdfDictionary pageDictSrc, PdfName box, float scale) {
        PdfArray original = pageDictSrc.getAsArray(box);
        if (original != null) {
            float width = original.getAsNumber(2).floatValue() - original.getAsNumber(0).floatValue();
            float height = original.getAsNumber(3).floatValue() - original.getAsNumber(1).floatValue();
            PdfArray result = new PdfArray();
            result.add(new PdfNumber(0));
            result.add(new PdfNumber(0));
            result.add(new PdfNumber(width * scale));
            result.add(new PdfNumber(height * scale));
            destPage.put(box, result);
        }
    }

}
