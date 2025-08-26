package com.example;

public abstract class Tile {

    int tileCoord;

    Tile(int tileCoord) {
        this.tileCoord = tileCoord;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();


    public static final class EmptyTile extends Tile{
        EmptyTile(int coord) {
            super(coord);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccuppiedTile extends Tile {

        Piece pieceOnTile;

        OccuppiedTile(int tileCoord, Piece pieceOnTile) {
            super(tileCoord);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }

    }

}
