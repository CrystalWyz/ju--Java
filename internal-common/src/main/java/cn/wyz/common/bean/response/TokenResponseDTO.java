package cn.wyz.common.bean.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangnanxiang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponseDTO {

    /**
     * 新 token
     */
    private String accessToken;

    /**
     * 旧 token
     */
    private String refreshToken;
}
