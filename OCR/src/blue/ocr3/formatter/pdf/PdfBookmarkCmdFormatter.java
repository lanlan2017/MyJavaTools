package blue.ocr3.formatter.pdf;

// import blue.ocr.formatter.Formatter;
// import blue.ocr2.formatter.Formatter;
import blue.ocr3.formatter.Formatter;
import tools.string.PdfBookmarkOcrStrCorrector;

public class PdfBookmarkCmdFormatter implements Formatter {
    String bookMarkPatten;

    public PdfBookmarkCmdFormatter(String bookMarkPatten) {
        this.bookMarkPatten = bookMarkPatten;
    }

    @Override
    public String format(String str) {
        String result;
        final PdfBookmarkOcrStrCorrector corrector = new PdfBookmarkOcrStrCorrector();
        switch (bookMarkPatten) {
            //shuqian111: tools.string.PdfBookmarkOcrStrCorrector.correctBookmark111
            //shuqian12: tools.string.PdfBookmarkOcrStrCorrector.correctBookmark12
            //shuqian21: tools.string.PdfBookmarkOcrStrCorrector.correctBookmark21
            //shuqian211: tools.string.PdfBookmarkOcrStrCorrector.correctBookmark211
            //shuqian22: tools.string.PdfBookmarkOcrStrCorrector.correctBookmark22
            //shuqian221: tools.string.PdfBookmarkOcrStrCorrector.correctBookmark221
            case "111":
                result = corrector.correctBookmark111(str);
                break;
            case "12":
                result = corrector.correctBookmark12(str);
                break;
            case "121":
                result = corrector.correctBookmark121(str);
                break;
            case "21":
                result = corrector.correctBookmark21(str);
                break;
            case "211":
                result = corrector.correctBookmark211(str);
                break;
            case "22":
                result = corrector.correctBookmark22(str);
                break;
            case "221":
                result = corrector.correctBookmark221(str);
                break;

            default:
                result = corrector.correctBookmark111(str);
                break;
        }
        return result;
    }
}