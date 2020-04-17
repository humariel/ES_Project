package breatheeasy.app.Requests;

import java.util.List;

public class RegularDataBlock {

    private String summary;
    private String icon;
    private List<RegularDataDict> data;

    public RegularDataBlock() {
        super();
    }

    public RegularDataBlock(String summary, String icon, List<RegularDataDict> data) {
        this.summary = summary;
        this.icon = icon;
        this.data = data;
    }

    public List<RegularDataDict> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "RegularDataBlock{" +
                "summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                ", data=" + data +
                '}';
    }
}
