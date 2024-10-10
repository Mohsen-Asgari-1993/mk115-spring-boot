package ir.maktabsharif115.springboot.config;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class CustomRequestMatcherBinder implements RequestMatchersBinder {

    @Override
    public String[] getPermitAllUrls() {
        return new String[]{
                "/user/register/**"
        };
    }

    @Override
    public Collection<RequestMatcherAuthorityPair> getAuthorityPairs() {
        return List.of(
                new RequestMatcherAuthorityPair() {
                    @Override
                    public String[] getUrls() {
                        return new String[]{
                                "/categories/admin/**"
                        };
                    }

                    @Override
                    public String[] getAuthorities() {
                        return new String[]{
                                "admin"
                        };
                    }
                }
        );
    }
}
