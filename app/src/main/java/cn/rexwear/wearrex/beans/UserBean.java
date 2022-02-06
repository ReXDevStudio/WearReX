package cn.rexwear.wearrex.beans;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserBean implements Serializable {

    @SerializedName("success")
    public Boolean success;
    @SerializedName("user")
    public UserDTO user;

    public static UserBean objectFromData(String str) {

        return new Gson().fromJson(str, UserBean.class);
    }

    public static UserBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), UserBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<UserBean> arrayUserBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<UserBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<UserBean> arrayUserBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<UserBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public static class UserDTO implements Serializable {
        @SerializedName("about")
        public String about;
        @SerializedName("activity_visible")
        public Boolean activityVisible;
        @SerializedName("allow_post_profile")
        public String allowPostProfile;
        @SerializedName("allow_receive_news_feed")
        public String allowReceiveNewsFeed;
        @SerializedName("allow_send_personal_conversation")
        public String allowSendPersonalConversation;
        @SerializedName("allow_view_identities")
        public String allowViewIdentities;
        @SerializedName("allow_view_profile")
        public String allowViewProfile;
        @SerializedName("avatar_urls")
        public AvatarUrlsDTO avatarUrls;
        @SerializedName("can_ban")
        public Boolean canBan;
        @SerializedName("can_converse")
        public Boolean canConverse;
        @SerializedName("can_edit")
        public Boolean canEdit;
        @SerializedName("can_follow")
        public Boolean canFollow;
        @SerializedName("can_ignore")
        public Boolean canIgnore;
        @SerializedName("can_post_profile")
        public Boolean canPostProfile;
        @SerializedName("can_view_profile")
        public Boolean canViewProfile;
        @SerializedName("can_view_profile_posts")
        public Boolean canViewProfilePosts;
        @SerializedName("can_warn")
        public Boolean canWarn;
        @SerializedName("content_show_signature")
        public Boolean contentShowSignature;
        @SerializedName("creation_watch_state")
        public String creationWatchState;
        @SerializedName("custom_fields")
        public CustomFieldsDTO customFields;
        @SerializedName("custom_title")
        public String customTitle;
        @SerializedName("dob")
        public DobDTO dob;
        @SerializedName("email")
        public String email;
        @SerializedName("email_on_conversation")
        public Boolean emailOnConversation;
        @SerializedName("gravatar")
        public String gravatar;
        @SerializedName("interaction_watch_state")
        public String interactionWatchState;
        @SerializedName("is_admin")
        public Boolean isAdmin;
        @SerializedName("is_discouraged")
        public Boolean isDiscouraged;
        @SerializedName("is_moderator")
        public Boolean isModerator;
        @SerializedName("is_staff")
        public Boolean isStaff;
        @SerializedName("is_super_admin")
        public Boolean isSuperAdmin;
        @SerializedName("last_activity")
        public Integer lastActivity;
        @SerializedName("location")
        public String location;
        @SerializedName("message_count")
        public Integer messageCount;
        @SerializedName("profile_banner_urls")
        public ProfileBannerUrlsDTO profileBannerUrls;
        @SerializedName("push_on_conversation")
        public Boolean pushOnConversation;
        @SerializedName("question_solution_count")
        public Integer questionSolutionCount;
        @SerializedName("reaction_score")
        public Integer reactionScore;
        @SerializedName("receive_admin_email")
        public Boolean receiveAdminEmail;
        @SerializedName("register_date")
        public Integer registerDate;
        @SerializedName("show_dob_date")
        public Boolean showDobDate;
        @SerializedName("show_dob_year")
        public Boolean showDobYear;
        @SerializedName("signature")
        public String signature;
        @SerializedName("timezone")
        public String timezone;
        @SerializedName("trophy_points")
        public Integer trophyPoints;
        @SerializedName("usa_tfa")
        public Boolean usaTfa;
        @SerializedName("user_group_id")
        public Integer userGroupId;
        @SerializedName("user_id")
        public Integer userId;
        @SerializedName("user_state")
        public String userState;
        @SerializedName("user_title")
        public String userTitle;
        @SerializedName("username")
        public String username;
        @SerializedName("view_url")
        public String viewUrl;
        @SerializedName("visible")
        public Boolean visible;
        @SerializedName("vote_score")
        public Integer voteScore;
        @SerializedName("website")
        public String website;
        @SerializedName("alert_optout")
        public List<?> alertOptout;
        @SerializedName("push_optout")
        public List<?> pushOptout;
        @SerializedName("secondary_group_ids")
        public List<Integer> secondaryGroupIds;

        public static UserDTO objectFromData(String str) {

            return new Gson().fromJson(str, UserDTO.class);
        }

        public static UserDTO objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), UserDTO.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<UserDTO> arrayUserDTOFromData(String str) {

            Type listType = new TypeToken<ArrayList<UserDTO>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<UserDTO> arrayUserDTOFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<UserDTO>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public static class AvatarUrlsDTO implements Serializable {
            String o;
            String h;
            String l;
            String m;
            String s;

            public String getO() {
                return o;
            }

            public void setO(String o) {
                this.o = o;
            }

            public String getH() {
                return h;
            }

            public void setH(String h) {
                this.h = h;
            }

            public String getL() {
                return l;
            }

            public void setL(String l) {
                this.l = l;
            }

            public String getM() {
                return m;
            }

            public void setM(String m) {
                this.m = m;
            }

            public String getS() {
                return s;
            }

            public void setS(String s) {
                this.s = s;
            }

            public static AvatarUrlsDTO objectFromData(String str) {

                return new Gson().fromJson(str, AvatarUrlsDTO.class);
            }

            public static AvatarUrlsDTO objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), AvatarUrlsDTO.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<AvatarUrlsDTO> arrayAvatarUrlsDTOFromData(String str) {

                Type listType = new TypeToken<ArrayList<AvatarUrlsDTO>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<AvatarUrlsDTO> arrayAvatarUrlsDTOFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<AvatarUrlsDTO>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }
        }

        public static class CustomFieldsDTO implements Serializable {
            public static CustomFieldsDTO objectFromData(String str) {

                return new Gson().fromJson(str, CustomFieldsDTO.class);
            }

            public static CustomFieldsDTO objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), CustomFieldsDTO.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<CustomFieldsDTO> arrayCustomFieldsDTOFromData(String str) {

                Type listType = new TypeToken<ArrayList<CustomFieldsDTO>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<CustomFieldsDTO> arrayCustomFieldsDTOFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<CustomFieldsDTO>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }
        }

        public static class DobDTO implements Serializable {
            @SerializedName("year")
            public Object year;
            @SerializedName("month")
            public Integer month;
            @SerializedName("day")
            public Integer day;

            public static DobDTO objectFromData(String str) {

                return new Gson().fromJson(str, DobDTO.class);
            }

            public static DobDTO objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), DobDTO.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<DobDTO> arrayDobDTOFromData(String str) {

                Type listType = new TypeToken<ArrayList<DobDTO>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<DobDTO> arrayDobDTOFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<DobDTO>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }
        }

        public static class ProfileBannerUrlsDTO implements Serializable {
            @SerializedName("l")
            public Object l;
            @SerializedName("m")
            public Object m;

            public static ProfileBannerUrlsDTO objectFromData(String str) {

                return new Gson().fromJson(str, ProfileBannerUrlsDTO.class);
            }

            public static ProfileBannerUrlsDTO objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), ProfileBannerUrlsDTO.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<ProfileBannerUrlsDTO> arrayProfileBannerUrlsDTOFromData(String str) {

                Type listType = new TypeToken<ArrayList<ProfileBannerUrlsDTO>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<ProfileBannerUrlsDTO> arrayProfileBannerUrlsDTOFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<ProfileBannerUrlsDTO>>() {
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
