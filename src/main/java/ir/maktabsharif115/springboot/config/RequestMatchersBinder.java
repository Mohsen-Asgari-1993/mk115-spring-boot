package ir.maktabsharif115.springboot.config;

import java.util.Collection;

public interface RequestMatchersBinder {

    String[] getPermitAllUrls();

    Collection<RequestMatcherAuthorityPair> getAuthorityPairs();

    interface RequestMatcherAuthorityPair {
        String[] getUrls();

        String[] getAuthorities();
    }
}
