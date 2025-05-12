package Controller.SirkBozorg;

import Model.App;
import Model.Game;
import Model.Map.Coordinate;
import Model.Player.Player;
import Model.Result;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MapController {
    public static Result printAllMap() {
        return new Result(true, App.getCurrentGame().getMap().toString());
    }

    public static Coordinate getDestination (Coordinate destination) {
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
    }
}
