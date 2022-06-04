package it.igesa.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author tarchoun Abir
 *
 */
public enum RobotsMetaTag {
    @JsonProperty("index")
    INDEX("index"),

    @JsonProperty("noindex")
    NOINDEX("noindex"),

    @JsonProperty("none")
    NONE("none"),

    @JsonProperty("follow")
    FOLLOW("follow"),

    @JsonProperty("nofollow")
    NOFOLLOW("nofollow"),

    @JsonProperty("noarchive")
    NOARCHIVE("noarchive"),

    @JsonProperty("nosnippet")
    NOSNIPPET("nosnippet"),

    @JsonProperty("noodp")
    NOODP("noodp"),

    @JsonProperty("noydir")
    NOYDIR("noydir"),

    @JsonProperty("noimageindex")
    NOIMAGEINDEX("noimageindex");

    private String tag;

    RobotsMetaTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    @JsonCreator
    public static RobotsMetaTag fromString(String robot) {
        return RobotsMetaTag.valueOf(robot.toUpperCase().trim());
    }
}
