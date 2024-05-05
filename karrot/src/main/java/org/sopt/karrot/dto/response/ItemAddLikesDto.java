package org.sopt.karrot.dto.response;

public record ItemAddLikesDto(
        Integer likes
) {
    public static ItemAddLikesDto from(final Integer likes) {
        return new ItemAddLikesDto(likes);
    }
}
