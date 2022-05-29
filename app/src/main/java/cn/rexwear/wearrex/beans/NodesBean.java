package cn.rexwear.wearrex.beans;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XC-Qan on 2022/5/28.
 * I'm very cute so please be nice to my code!
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 * 给！爷！写！注！释！
 */

public class NodesBean {

    @SerializedName("nodes_flat")
    public List<NodesFlatDTO> nodesFlat;

    public static NodesBean objectFromData(String str) {

        return new Gson().fromJson(str, NodesBean.class);
    }

    public static NodesBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), NodesBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<NodesBean> arrayNodesBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<NodesBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<NodesBean> arrayNodesBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<NodesBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public static class NodesFlatDTO {
        @SerializedName("node")
        public NodeDTO node;
        @SerializedName("depth")
        public Integer depth;

        public static NodesFlatDTO objectFromData(String str) {

            return new Gson().fromJson(str, NodesFlatDTO.class);
        }

        public static NodesFlatDTO objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), NodesFlatDTO.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<NodesFlatDTO> arrayNodesFlatDTOFromData(String str) {

            Type listType = new TypeToken<ArrayList<NodesFlatDTO>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<NodesFlatDTO> arrayNodesFlatDTOFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<NodesFlatDTO>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public static class NodeDTO {
            @SerializedName("breadcrumbs")
            public List<?> breadcrumbs;
            @SerializedName("description")
            public String description;
            @SerializedName("display_in_list")
            public Boolean displayInList;
            @SerializedName("display_order")
            public Integer displayOrder;
            @SerializedName("node_id")
            public Integer nodeId;
            @SerializedName("node_name")
            public Object nodeName;
            @SerializedName("node_type_id")
            public String nodeTypeId;
            @SerializedName("parent_node_id")
            public Integer parentNodeId;
            @SerializedName("title")
            public String title;
            @SerializedName("type_data")
            public TypeDataDTO typeData;
            @SerializedName("view_url")
            public String viewUrl;

            public static NodeDTO objectFromData(String str) {

                return new Gson().fromJson(str, NodeDTO.class);
            }

            public static NodeDTO objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), NodeDTO.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<NodeDTO> arrayNodeDTOFromData(String str) {

                Type listType = new TypeToken<ArrayList<NodeDTO>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<NodeDTO> arrayNodeDTOFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<NodeDTO>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public static class TypeDataDTO {
                public static TypeDataDTO objectFromData(String str) {

                    return new Gson().fromJson(str, TypeDataDTO.class);
                }

                public static TypeDataDTO objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), TypeDataDTO.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<TypeDataDTO> arrayTypeDataDTOFromData(String str) {

                    Type listType = new TypeToken<ArrayList<TypeDataDTO>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<TypeDataDTO> arrayTypeDataDTOFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<TypeDataDTO>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }
            }
        }
    }
}
