package regex;

/**
 * @author francis
 * create at 2019/12/17-16:37
 */
public class UrlEscape {
    /**
     * 修复URL.
     *
     * @param headerName
     * @return
     */
    public static String escapeURL(String headerName) {
        String AnchorName;
        AnchorName = headerName.replace("\\", "/");
        AnchorName = AnchorName.replaceAll(Regex.HexoNextUrl1.getRegex(), "-");
        AnchorName = AnchorName.replaceAll(Regex.HexoNextUrl2.getRegex(), "");
        AnchorName = AnchorName.replaceAll(Regex.HexoNextUrl3.getRegex(), "/");
        return AnchorName;
    }
}
