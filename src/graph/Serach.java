package graph;

public class Serach {
    private char[] mVexs;
    private int[][] mMatrix;

    /*
     * 创建图(用已提供的矩阵)
     *
     * 参数说明：
     *     vexs  -- 顶点数组
     *     edges -- 边数组
     */
    public Serach(char[] vexs, char[][] edges) {
        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;
        int elen = edges.length;
        // 初始化"顶点"
        mVexs = new char[vlen];
        for (int i = 0; i < mVexs.length; i++)
            mVexs[i] = vexs[i];
        // 初始化"边"
        mMatrix = new int[vlen][vlen];
        for (int i = 0; i < elen; i++) {
            // 读取边的起始顶点和结束顶点
            int p1 = getPosition(edges[i][0]);
            int p2 = getPosition(edges[i][1]);
            mMatrix[p1][p2] = 1;
            mMatrix[p2][p1] = 1;
        }
    }
    /*
     * 返回ch位置
     */
    private int getPosition(char ch) {
        for(int i=0; i<mVexs.length; i++)
            if(mVexs[i]==ch)
                return i;
        return -1;
    }

    /*
     * 返回顶点v的第一个邻接顶点的索引，失败则返回-1
     */
    private int firstVertex(int v){
        if(v < 0 || v > (mVexs.length-1)){
            return -1;
        }
        for(int i=0;i<mVexs.length;i++){
            if(mMatrix[v][i] == 1){
                return i;
            }
        }
        return -1;
    }

    /*
     * 返回顶点v相对于w的下一个邻接顶点的索引，失败则返回-1
     */
    private int nextVertex(int v,int w){
        if(v < 0 || v > (mVexs.length-1) || w < 0 || w > (mVexs.length-1)){
            return -1;
        }
        for(int i=w+1;i<mVexs.length;i++){
            if(mMatrix[v][i] == 1){
                return i;
            }
        }
        return -1;
    }

    /*
     * 深度优先搜索遍历图
     */
    private void DFS(){
        boolean[] visited = new boolean[mVexs.length];
        System.out.print("DFS: ");
        for(int i=0;i<mVexs.length;i++){
            if(!visited[i]){
                DFS(i,visited);
            }
        }
        System.out.print("\n");
    }
    /*
     * 深度优先搜索遍历图的递归实现
     */
    private void DFS(int i,boolean[] visited){
        visited[i] = true;
        System.out.print(mVexs[i]+",");

        for(int w = firstVertex(i);w >= 0;w = nextVertex(i,w)){
            if(!visited[w]){
                DFS(w,visited);
            }
        }
    }
    /*
     * 广度优先搜索（类似于树的层次遍历）
     */
    private void BFS(){
        int head = 0;
        int rear = 0;
        int[] queue = new int[mVexs.length];
        boolean[] visited = new boolean[mVexs.length];
        System.out.print("BFS:");
        for(int i=0;i<mVexs.length;i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.print(mVexs[i] + ",");
                queue[rear++] = i;
            }
            while (head != rear) {
                int j = queue[head++];
                for (int w = firstVertex(j); w >= 0; w = nextVertex(j, w)) {
                    if (!visited[w]) {
                        visited[w] = true;
                        System.out.print(mVexs[w] + ",");
                        queue[rear++] = w;
                    }
                }
            }
        }
        System.out.print("\n");
    }

    /*
     * 打印矩阵队列图
     */
    public void print() {
        System.out.printf("Martix Graph:\n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%d ", mMatrix[i][j]);
            System.out.printf("\n");
        }
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'F', 'G'}};
        Serach pG;
        // 自定义"图"(输入矩阵队列)
        //pG = new MatrixUDG();
        // 采用已有的"图"
        pG = new Serach(vexs, edges);
        pG.print();   // 打印图
        pG.DFS();     // 深度优先遍历
        pG.BFS();     // 广度优先遍历
    }
}
