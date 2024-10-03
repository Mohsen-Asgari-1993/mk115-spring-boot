package ir.maktabsharif115.springboot.config;

import java.util.Collection;

public class DefaultRequestMatchersBinder implements RequestMatchersBinder {

    @Override
    public String[] getPermitAllUrls() {
        return null;
    }

    @Override
    public Collection<RequestMatcherAuthorityPair> getAuthorityPairs() {
        return null;
    }


}
