package cn.rexwear.wearrex.managers;

import java.util.ArrayList;
import java.util.List;

import cn.rexwear.wearrex.beans.NodesBean;
import cn.rexwear.wearrex.utils.NetworkUtils;
import okhttp3.Callback;

/**
 * Created by XC-Qan on 2022/5/28.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

public class ForumManager {
    public static void getAllNodes(Callback callback) {
        NetworkUtils.getUrl("/nodes/flattened", callback);
    }

    public static List<NodesBean.NodesFlatDTO.NodeDTO> getAllNodesByDepth(NodesBean nodesBean, int depth) {
        List<NodesBean.NodesFlatDTO.NodeDTO> tempList = new ArrayList<>();
        for (NodesBean.NodesFlatDTO nodeFlat :
                nodesBean.nodesFlat) {
            if (nodeFlat.depth == depth) {
                tempList.add(nodeFlat.node);
            }
        }
        return tempList;
    }
}
