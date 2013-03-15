/**
 * 
 */
package org.iplantc.core.uicommons.client.collaborators.util;

import org.iplantc.core.uicommons.client.collaborators.models.Collaborator;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent.BeforeLoadHandler;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.form.ComboBox;

/**
 * @author sriram
 *
 */
public class UserSearchField implements IsWidget {

    private final UserSearchRPCProxy searchProxy;

    private ComboBox<Collaborator> combo;

    interface UserTemplate extends XTemplates {
        @XTemplate("<div> {c.firstName} {c.lastName} <br/> {c.email} </div>")
        SafeHtml render(Collaborator c);
    }

    public interface UsersLoadConfig extends PagingLoadConfig {
        String getQuery();

        void setQuery(String query);
    }

    interface UserSearchAutoBeanFactory extends AutoBeanFactory {
        static UserSearchAutoBeanFactory instance = GWT.create(UserSearchAutoBeanFactory.class);

        AutoBean<UsersLoadConfig> loadConfig();

    }

    public UserSearchField() {
        this.searchProxy = new UserSearchRPCProxy();
        PagingLoader<UsersLoadConfig, PagingLoadResult<Collaborator>> loader = new PagingLoader<UsersLoadConfig, PagingLoadResult<Collaborator>>(
                searchProxy);
        UsersLoadConfig loadConfig = UserSearchAutoBeanFactory.instance.loadConfig().as();
        loader.useLoadConfig(loadConfig);
        loader.addBeforeLoadHandler(new BeforeLoadHandler<UsersLoadConfig>() {

            @Override
            public void onBeforeLoad(BeforeLoadEvent<UsersLoadConfig> event) {
                String query = combo.getText();
                if (query != null && !query.equals("")) {
                    event.getLoadConfig().setQuery(query);
                }

            }
        });
        
        ListStore<Collaborator> store = new ListStore<Collaborator>(new ModelKeyProvider<Collaborator>() {

                    @Override
                    public String getKey(Collaborator item) {
                        return item.getId();
                    }
            
                });
        loader.addLoadHandler(new LoadResultListStoreBinding<UsersLoadConfig, Collaborator, PagingLoadResult<Collaborator>>(
                store));
        
        
        final UserTemplate template = GWT.create(UserTemplate.class);
        
        ListView<Collaborator, Collaborator> view = new ListView<Collaborator, Collaborator>(store,
                new IdentityValueProvider<Collaborator>());
        
        
        view.setCell(new AbstractCell<Collaborator>() {

            @Override
            public void render(com.google.gwt.cell.client.Cell.Context context, Collaborator value,
                    SafeHtmlBuilder sb) {
                sb.append(template.render(value));
            }
            
        });
        
        
        ComboBoxCell<Collaborator> cell = new ComboBoxCell<Collaborator>(store,
                new StringLabelProvider<Collaborator>() {
            
            @Override
            public String getLabel(Collaborator c) {
                        return c.getFirstName() + " " + c.getLastName();
            }
            
        }, view);
        combo = new ComboBox<Collaborator>(cell);
        combo.setLoader(loader);
        combo.setPageSize(10);
        combo.setWidth(300);
        combo.setHideTrigger(true);
    }

    @Override
    public Widget asWidget() {
        return combo;
    }

}
