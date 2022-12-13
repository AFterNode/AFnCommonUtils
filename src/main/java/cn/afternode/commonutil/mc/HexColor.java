package cn.afternode.commonutil.mc;

/**
 * Server must have CMI/EssentialsX/ChatColor plugin
 */
public class HexColor {
    private final int hex;

    /**
     *
     * @param hex e.g. 0xFFFFFF
     */
    public HexColor(int hex) {
        this.hex = hex;
    }

    @Override
    public String toString() {
        return "{#" + Integer.toHexString(hex) + "}";
    }
}
