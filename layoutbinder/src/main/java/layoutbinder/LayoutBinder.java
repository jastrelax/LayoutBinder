/*
 * Copyright 2018 yinpinjiu@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package layoutbinder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import layoutbinder.runtime.ActivityLayoutBinder;
import layoutbinder.runtime.FragmentLayoutBinder;
import layoutbinder.runtime.LayoutBinding;
import layoutbinder.runtime.LayoutBindingFactory;
import layoutbinder.runtime.ViewLayoutBinder;

public class LayoutBinder {

    public static LayoutBinding bind(Activity activity) {
        LayoutBinding layoutBinding = createLayoutBinding(activity);
        if (layoutBinding == null) {
            return null;
        }
        ((ActivityLayoutBinder) layoutBinding).bind(activity);
        return layoutBinding;
    }

    public static LayoutBinding bind(
            Fragment fragment, LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        LayoutBinding layoutBinding = createLayoutBinding(fragment);
        if (layoutBinding == null) {
            return null;
        }
        ((FragmentLayoutBinder) layoutBinding).bind(fragment, inflater, parent, attachToParent);
        return layoutBinding;
    }

    public static LayoutBinding bind(Fragment fragment, LayoutInflater inflater, ViewGroup parent) {
        LayoutBinding layoutBinding = createLayoutBinding(fragment);
        if (layoutBinding == null) {
            return null;
        }
        ((FragmentLayoutBinder) layoutBinding).bind(fragment, inflater, parent);
        return layoutBinding;
    }

    public static LayoutBinding bind(ViewGroup viewGroup) {
        LayoutBinding layoutBinding = createLayoutBinding(viewGroup);
        if (layoutBinding == null)
            return null;
        ((ViewLayoutBinder) layoutBinding).bind(viewGroup);
        return layoutBinding;
    }

    public static LayoutBinding bind(ViewGroup viewGroup, boolean attachToParent) {
        LayoutBinding layoutBinding = createLayoutBinding(viewGroup);
        if (layoutBinding == null)
            return null;
        ((ViewLayoutBinder) layoutBinding).bind(viewGroup, attachToParent);
        return layoutBinding;
    }

    private static LayoutBinding createLayoutBinding(Object target) {
        LayoutBindingFactory factory = LayoutBindingFactoryMapper.get(target);
        if (null == factory) {
            return null;
        }
        LayoutBinding binding = factory.create();
        return binding;
    }
}
