package idea.plugin.psiviewer.controller.actions;

import cn.hutool.http.HttpUtil;
import com.intellij.openapi.diagnostic.Logger;

import java.util.Map;

public class jumpto_IdaAction extends BaseIdaAction {
    private final Logger LOGGER = Logger.getInstance(jumpto_IdaAction.class);


    @Override
    protected void handler(String ea) {
        String url = buildUrl("/jumpto");
        HttpUtil.get(url, Map.of("ea", ea));
    }
}
