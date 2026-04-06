package app;

public class TableViewFactory implements ViewFactory {
    private int tableWidth;

    public TableViewFactory(int width) {
        this.tableWidth = width;
    }

    @Override
    public ResultView createView() {
        return new TableView(tableWidth);
    }
}
