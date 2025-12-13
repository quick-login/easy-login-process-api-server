package co.kr.easylogin.easylogin.member.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    USER(Authority.USER),
    MANAGER(Authority.MANAGER),
    ADMIN(Authority.ADMIN);

    private final String authority;

    public static class Authority {

        public static final String USER = "ROLE_USER";
        public static final String MANAGER = "ROLE_MANAGER";
        public static final String ADMIN = "ROLE_ADMIN";

    }
}
