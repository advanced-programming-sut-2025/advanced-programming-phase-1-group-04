package Controller.SirkBozorg;

import Controller.GameMenuController;
import Model.App;
import Model.Map.*;
import Model.Player.Player;
import Model.Result;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MapController {
    public static Result printAllMap() {
        return new Result(true, App.getCurrentGame().getMap().toString());
    }

    public static Result printFarm() {
        int i = 0, j = 0;
        switch (App.getCurrentGame().getCurrentPlayer().getFarm()) {
            case 2:
                j = 2;
            case 3:
                i = 2;
                j = 2;
            case 4:
                i = 2;
        }
        return new Result(true, App.getCurrentGame().getMap().getRegion(i, j).toString());
    }

    public static Result printMap(String stringX, String stringY, String stringSize) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        int x = Integer.parseInt(stringX);
        int y = Integer.parseInt(stringY);
        int size = Integer.parseInt(stringSize);

        if ((x < 0 || x >= 90) || (y < 0 || y >= 120)) {
            return new Result(false, "Mashti x,y bein (0,0) - (89, 119)");
        } else if ((x + size < 0 || x + size >= 90) || (y + size < 0 || y + size >= 120)) {
            return new Result(false, "Size is invalid. Mashti x,y bein (0,0) - (89, 119)");
        }

        StringBuilder result = new StringBuilder();
        Tile[][] fullMap = App.getCurrentGame().getMap().getFullMap();
        for (int i = x; i < x + size; i++){
            for(int j = y; j < y +size; j++) {
                result.append(" ");
                Coordinate coordinate = new Coordinate(i, j);
                boolean isPlayer = false;
                for (Player player: App.getCurrentGame().getPlayers()) {
                    if (player.getCoordinate().equals(coordinate)) {
                        isPlayer = true;
                        if (player.getId() == App.getCurrentGame().getCurrentPlayer().getId())
                            result.append(Symbols.CurrentPlayer.getColoredSymbol());
                        else
                            result.append(Symbols.Player.getColoredSymbol());
                    }
                }
                if (!isPlayer)
                    result.append(fullMap[i][j].getSymbol());
                result.append(" ");
            }
            result.append("\n");
        }
        return new Result(true, result.toString());
    }

    public static Result helpMap(){
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        StringBuilder result = new StringBuilder();
        for (Symbols s: Symbols.values()) {
            result.append(s.name()).append(" ").append(s.getColoredSymbol()).append("\n");
        }

        return new Result(true, result.toString());
    }

    public static Result walk(String stringX, String stringY) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        int x = Integer.parseInt(stringX);
        int y = Integer.parseInt(stringY);
        Coordinate coordinate = new Coordinate(x, y);
        int energy = getDestinationEnergy(coordinate);

        if ((x < 0 || x >= 90) || (y < 0 || y >= 120)) {
            return new Result(false, "Mashti x,y bein (0,0) - (89, 119)!");
        } else if (energy < 0) {
            return new Result(false, "You can't go there!");
        }

        return new Result(true, "Required Energy: " + energy + "\nDo you want to go?");
    }

    public static Result walk(String input, String stringX, String stringY) {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        int x = Integer.parseInt(stringX);
        int y = Integer.parseInt(stringY);
        Coordinate coordinate = new Coordinate(x, y);
        int energy = getDestinationEnergy(coordinate);

        if (!input.toLowerCase().contains("yes")) {
            return new Result(false, "Fekresho nemikardi na?");
        }

        App.getCurrentGame().getCurrentPlayer().addEnergy(-energy);
        App.getCurrentGame().getCurrentPlayer().setCoordinate(getDestination(coordinate));
        return new Result(true, "You successfully go to (" + x +", " + y + ")");
    }

    public static Result buildGreenHouse() {
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
        Player player = App.getCurrentGame().getCurrentPlayer();
        if (player.getCount() < 1000) {
            return new Result(false, "You don't have enough money!(1000 dollars)");
        } else if (!player.getInventory().hasItemWithNumber("Stone", 500)) {
            return new Result(false, "You don't have enough stone!(500)");
        }

        player.getInventory().removeItem("stone", 500);
        player.addCount(-1000);
        int x = 4,y =5;
        switch (player.getFarm()) {
            case 2:
                y = 5 + 80;
                break;
            case 3:
                x = 4 + 60;
                y = 5 + 80;
                break;
            case 4:
                x = 4 + 60;
                break;
        }

        App.getCurrentGame().getMap().build(x, y, BuildingType.GreenHouserBuild);
        return new Result(true, "Now you have Greenhouse:)");
    }

    /*public static Coordinate getDestination (Coordinate destination) {
        int lenx = Math.abs(destination.getX() - App.getCurrentGame().getCurrentPlayer().getCoordinate().getX());
        int leny = Math.abs(destination.getY() - App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
        int minx = Math.min(destination.getX(), App.getCurrentGame().getCurrentPlayer().getCoordinate().getX());
        int miny = Math.min(destination.getY(), App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
        int sourcex = App.getCurrentGame().getCurrentPlayer().getCoordinate().getX() - lenx;
        int sourcey = App.getCurrentGame().getCurrentPlayer().getCoordinate().getY() - leny;
        int destx = destination.getX() - minx;
        int desty = destination.getY() - miny;
        int[][][] dist = new int[lenx][leny][4];
        for (int[][] row : dist) {
            for (int[] col : row)
                Arrays.fill (col , Integer.MAX_VALUE);
        }
        final int[] dx = {-1, 1, 0, 0};
        final int[] dy = {0, 0, -1, 1};
        PriorityQueue <int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < 4; i++) {
            dist[sourcex][sourcey][i] = 0;
            pq.offer(new int[]{0, sourcex, sourcey, i});
        }
        Coordinate last = new Coordinate(sourcex, sourcey);
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int x = cur[1];
            int y = cur[2];
            int dir = cur[3];
            if ((cost / 20) > App.getCurrentGame().getCurrentPlayer().getEnergy())
                return last;
            last.setX(x);
            last.setY(y);
            if (x == destx && y == desty)
                return last;
            for (int i = 0; i < 4; i++) {
                int newx = x + dx[i];
                int newy = y + dy[i];
                if (newx < 0 || newx >= lenx || newy < 0 || newy >= leny)
                    continue;
                int newCost = cost + 1;
                if (dir != i)
                    newCost += 10;
                if (newCost < dist[newx][newy][i]) {
                    dist[newx][newy][i] = newCost;
                    pq.offer(new int[]{newCost, newx, newy, i});
                }
            }
        }
        last.setX(-1);
        last.setY(-1);
        return last;
    }

    public static int getDestinationEnergy (Coordinate destination) {
        int lenx = Math.abs(destination.getX() - App.getCurrentGame().getCurrentPlayer().getCoordinate().getX());
        int leny = Math.abs(destination.getY() - App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
        int minx = Math.min(destination.getX(), App.getCurrentGame().getCurrentPlayer().getCoordinate().getX());
        int miny = Math.min(destination.getY(), App.getCurrentGame().getCurrentPlayer().getCoordinate().getY());
        int sourcex = App.getCurrentGame().getCurrentPlayer().getCoordinate().getX() - lenx;
        int sourcey = App.getCurrentGame().getCurrentPlayer().getCoordinate().getY() - leny;
        int destx = destination.getX() - minx;
        int desty = destination.getY() - miny;
        int[][][] dist = new int[lenx][leny][4];
        for (int[][] row : dist) {
            for (int[] col : row)
                Arrays.fill (col , Integer.MAX_VALUE);
        }
        final int[] dx = {-1, 1, 0, 0};
        final int[] dy = {0, 0, -1, 1};
        PriorityQueue <int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < 4; i++) {
            dist[sourcex][sourcey][i] = 0;
            pq.offer(new int[]{0, sourcex, sourcey, i});
        }
        Coordinate last = new Coordinate(sourcex, sourcey);
        int ans = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int x = cur[1];
            int y = cur[2];
            int dir = cur[3];
            if ((cost / 20) > App.getCurrentGame().getCurrentPlayer().getEnergy())
                return ans;
            last.setX(x);
            last.setY(y);
            ans = cost / 20;
            if (x == destx && y == desty)
                return ans;
            for (int i = 0; i < 4; i++) {
                int newx = x + dx[i];
                int newy = y + dy[i];
                if (newx < 0 || newx >= lenx || newy < 0 || newy >= leny)
                    continue;
                int newCost = cost + 1;
                if (dir != i)
                    newCost += 10;
                if (newCost < dist[newx][newy][i]) {
                    dist[newx][newy][i] = newCost;
                    pq.offer(new int[]{newCost, newx, newy, i});
                }
            }
        }
        return -1;
    }*/

    public static int getDestinationEnergy(Coordinate destination) {
        Coordinate start = App.getCurrentGame().getCurrentPlayer().getCoordinate();

        int maxEnergy = App.getCurrentGame().getCurrentPlayer().getEnergy();

        int width = 90;
        int height = 120;

        int[][][] dist = new int[height][width][4];
        for (int[][] row : dist)
            for (int[] col : row)
                Arrays.fill(col, Integer.MAX_VALUE);

        final int[] dx = {-1, 1, 0, 0};
        final int[] dy = {0, 0, -1, 1};

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int dir = 0; dir < 4; dir++) {
            dist[start.getY()][start.getX()][dir] = 0;
            pq.offer(new int[]{0, start.getX(), start.getY(), dir});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], x = cur[1], y = cur[2], dir = cur[3];

            int energyUsed = cost / 20;
            if (energyUsed > maxEnergy) return -1;

            if (x == destination.getX() && y == destination.getY())
                return energyUsed;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= width || ny >= height)
                    continue;
                Coordinate coor = new Coordinate(nx, ny);
                Tile tile = App.getCurrentGame().getTile(coor);
                if (tile == null || !tile.isWalkable())  // مهم‌ترین بخش
                    continue;

                int newCost = cost + 1;
                if (dir != i) newCost += 10;

                if (newCost < dist[ny][nx][i]) {
                    dist[ny][nx][i] = newCost;
                    pq.offer(new int[]{newCost, nx, ny, i});
                }
            }
        }

        return -1;
    }

    public static Coordinate getDestination(Coordinate destination) {
        int energyNeeded = getDestinationEnergy(destination);
        if (energyNeeded == -1) {
            return new Coordinate(-1, -1);  // unreachable
        }
        return destination;
    }
}
