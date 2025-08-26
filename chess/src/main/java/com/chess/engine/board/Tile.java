package com.chess.engine.board;

// Tile.java
// Represents a single square (tile) on the chess board.
// Tiles can be either empty or occupied by a chess piece.

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

public abstract class Tile {

    // The coordinate (0-63) of this tile on the board.
    protected final int tileCoord;

    // Immutable map of all possible empty tiles for quick lookup.
    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    // Creates all possible empty tiles and stores them in an immutable map.
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i = 0; i < 64; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        // Returns an immutable copy of the map.
        return ImmutableMap.copyOf(emptyTileMap);
    }

    // Factory method to create a tile: occupied if a piece is present, otherwise empty.
    public static Tile createTile(final int tileCoord, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoord, piece) : EMPTY_TILES.get(tileCoord);
    }

    // Constructor for Tile, only accessible by subclasses.
    private Tile(int tileCoord) {
        this.tileCoord = tileCoord;
    }

    // Returns true if the tile is occupied by a piece.
    public abstract boolean isTileOccupied();

    // Returns the piece on the tile, or null if empty.
    public abstract Piece getPiece();

    // EmptyTile subclass represents a tile with no piece.
    public static final class EmptyTile extends Tile{
        private EmptyTile(final int coord) {
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

    // OccupiedTile subclass represents a tile with a piece.
    public static final class OccupiedTile extends Tile {

        private final Piece pieceOnTile;

        private OccupiedTile(int tileCoord, Piece pieceOnTile) {
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
