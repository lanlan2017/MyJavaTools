package regex;

/**
 * @author francis
 * create at 2019/12/17-16:37
 */
public class UrlCheck {
    /**
     * 修复URL.
     *
     * @param headerName
     * @return
     */
    public static String checkURL(String headerName) {
        String AnchorName;
        AnchorName = headerName.replace("\\", "/");
        AnchorName = AnchorName.replaceAll(Regex.HexoNextUrl1.getRegex(), "-");
        AnchorName = AnchorName.replaceAll(Regex.HexoNextUrl2.getRegex(), "");
        return AnchorName;
    }
}
