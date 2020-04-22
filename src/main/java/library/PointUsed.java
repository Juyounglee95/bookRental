
package library;

public class PointUsed extends AbstractEvent {

    private Long id;
    private Long pointQty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getPointQty() {
        return pointQty;
    }

    public void setPointQty(Long pointQty) {
        this.pointQty = pointQty;
    }
}
