/**
 * Created by mayu8 on 2016/12/11.
 */
public class Dfs {

    public boolean dfs(int[][] puzzle,int i,int j){
        if(i==15&&j>=16) return true;
        if(j==16) return dfs(puzzle,i+1,0);

        if(puzzle[i][j]!=-1){
            return dfs(puzzle,i,j+1);  //next cell in the same row
        }
        else{
            for(int num=1;num<=16;num++){
                //System.out.println("trying"+i+","+j+","+num);
                if(valid(puzzle,i,j,num)){
                    //System.out.println(String.valueOf(num));
                    puzzle[i][j]=num;
                    if(dfs(puzzle,i,j+1)){
                        return true;
                    }
                }
                //else return false;
            }
            puzzle[i][j]=-1;
        }
        return false;
    }
    public boolean valid(int[][] puzzle,int x,int y,int num){
        for(int i=0;i<16;i++){
            if(puzzle[i][y]==num)
                return false;

        }
        for(int j=0;j<16;j++){
            if(puzzle[x][j]==num)
                return false;

        }
        int c=(x/4)*4;
        int r=(y/4)*4;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(puzzle[c+i][r+j]==num)
                    return false;

            }

        }
        return true;

    }
}
