package org.iplantc.core.uicommons.client.models;

import com.google.common.base.Strings;
import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.Splittable;
import com.google.web.bindery.autobean.shared.impl.StringQuoter;

public class CommonModelUtils {
    private static final CommonModelAutoBeanFactory factory = GWT.create(CommonModelAutoBeanFactory.class);

    public static HasId createHasIdFromString(String id) {
        Splittable hasIdSplittable = createHasIdSplittableFromString(id);

        if (hasIdSplittable == null) {
            return null;
        }

        return AutoBeanCodex.decode(factory, HasId.class, hasIdSplittable).as();
    }

    public static Splittable createHasIdSplittableFromString(String id) {
        if (Strings.isNullOrEmpty(id)) {
            return null;
        }

        Splittable hasIdSplittable = StringQuoter.createSplittable();
        StringQuoter.create(id).assign(hasIdSplittable, "id");

        return hasIdSplittable;
    }

    public static HasId createHasIdFromSplittable(Splittable value) {
        if ((value == null) || !(value.isKeyed() && (value.get("id") != null) && (value.get("id").isString())))
            return null;

        return AutoBeanCodex.decode(factory, HasId.class, value).as();
    }
}
