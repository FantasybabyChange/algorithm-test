package com.fantasybaby.xiaohui.chapter6;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A*寻路算法
 * 2019/12/22
 *
 * @authorfantasybaby
 **/
public class ASearchPath {
    public static int[][] MAZE = {
            {0,0,0,1,0,0,0},
            {0,0,0,1,0,0,0},
            {0,0,1,1,0,0,0},
            {0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0}
    };
    @Data
    @NoArgsConstructor
    static class Grid{
        public int x;
        public int y;
        public int f;
        public int g;
        public int h;
        public Grid parent;
        public Grid(int x,int y){
            this.x = x;
            this.y = y;
        }
        public void intGrid(Grid parent,Grid end){
            this.parent = parent;
            if(parent != null){
                this.g = parent.g + 1;
            }else{
                this.g = 1;
            }
            this.h = Math.abs(this.x-end.x)+Math.abs(this.y-end.y);
            this.f = h+g;
        }
    }
    public Grid aSearch(Grid start,Grid end){
        List<Grid> openGrid = Lists.newArrayList();
        List<Grid> closeGrid = Lists.newArrayList();
        openGrid.add(start);
        while (openGrid.size() > 0) {
            Grid minGrid = findMinGrid(openGrid);
            openGrid.remove(minGrid);
            closeGrid.add(minGrid);
            List<Grid> neighbors = findNeighbors(minGrid, openGrid, closeGrid);
            for (Grid neighbor : neighbors) {
                if(!openGrid.contains(neighbor)){
                    neighbor.intGrid(minGrid,end);
                    openGrid.add(neighbor);
                }
            }
            for (Grid grid : openGrid) {
                if(grid.x == end.x && grid.y == end.y){
                    return grid;
                }
            }
        }
        return null;
    }
    private List<Grid> findNeighbors(Grid grid,List<Grid> open,List<Grid> close){
        List<Grid> newGrids = Lists.newArrayList();
        int x = grid.x;
        int y = grid.y;
        if(isValidGrid(x-1,y,open,close)){
            newGrids.add(new Grid(x-1,y));
        }
        if(isValidGrid(x,y-1,open,close)){
            newGrids.add(new Grid(x,y-1));
        }
        if(isValidGrid(x,y+1,open,close)){
            newGrids.add(new Grid(x,y+1));
        }
        if(isValidGrid(x+1,y,open,close)){
            newGrids.add(new Grid(x+1,y));
        }
        return newGrids;
    }
    /**
     *
     * @param x
     * @param y
     * @param openLIst
     * @param closeList
     * @return
     */
    private boolean isValidGrid(int x, int y, List<Grid> openLIst, List<Grid> closeList){
        if(x < 0 || x >=MAZE.length|| y<0 || y > MAZE[0].length){
            return  false;
        }
        if(MAZE[x][y] == 1){
            return false;
        }
        if(containGrid(openLIst,x,y)){
            return false;
        }
        if(containGrid(closeList,x,y)){
            return false;
        }
        return true;
    }
    /**
     * 获取最小F Grid
     * @param openGrid
     * @return
     */
    private Grid findMinGrid(List<Grid> openGrid){
        Grid minGrid = openGrid.get(0);
        for (int i = 1; i < openGrid.size(); i++) {
            if(minGrid.f > openGrid.get(i).f){
                minGrid = openGrid.get(i);
            }
        }
        return minGrid;
    }
    /**
     * 查出匹配的grid
     * @param openGrid
     * @param x
     * @param y
     * @return
     */
    private boolean containGrid(List<Grid> openGrid,int x,int y){
        return openGrid.stream().anyMatch(a->a.x == x && a.y == y);
    }

    public static void main(String[] args) {
        Grid start = new Grid(2,1);
        Grid end = new Grid(2,5);
        ASearchPath aSearchPath = new ASearchPath();
        Grid grid = aSearchPath.aSearch(start, end);
        List<Grid> path = Lists.newArrayList();
        while (grid != null) {
            path.add(new Grid(grid.x,grid.y));
            grid = grid.getParent();
        }
        int[][] maze = ASearchPath.MAZE;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if(aSearchPath.containGrid(path,i,j)){
                    System.out.print(" * ");
                }else if(maze[i][j] == 1){
                    System.out.print(" | ");
                }else{
                    System.out.print(" , ");
                }
            }
            System.out.println();
        }
    }
}
