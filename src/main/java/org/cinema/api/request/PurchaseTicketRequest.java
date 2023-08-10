package org.cinema.api.request;

public class PurchaseTicketRequest {
    private int row;
    private int column;

    public PurchaseTicketRequest(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public PurchaseTicketRequest() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
