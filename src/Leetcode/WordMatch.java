package Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordMatch {
    public static void main(String[] args) {
        List dict = new ArrayList();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dogs");
        System.out.println(new WordMatch().wordBreak("catsanddogs", new HashSet<>(dict)));
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
        ArrayList<String> [] pos = new ArrayList[s.length()+1];
        pos[0]=new ArrayList<String>();

        for(int i=0; i<s.length(); i++){
            if(pos[i]!=null){
                for(int j=i+1; j<=s.length(); j++){
                    String sub = s.substring(i,j);
                    if(wordDict.contains(sub)){
                        if(pos[j]==null){
                            ArrayList<String> list = new ArrayList<String>();
                            list.add(sub);
                            pos[j]=list;
                        }else{
                            pos[j].add(sub);
                        }

                    }
                }
            }
        }

        if(pos[s.length()]==null){
            return new ArrayList<String>();
        }else{
            ArrayList<String> result = new ArrayList<String>();
            dfs(pos, result, "", s.length());
            return result;
        }
    }

    public void dfs(ArrayList<String> [] pos, ArrayList<String> result, String curr, int i){
        if(i==0){
            result.add(curr.trim());
            return;
        }

        for(String s: pos[i]){
            String combined = s + " "+ curr;
            dfs(pos, result, combined, i-s.length());
        }
    }
}
