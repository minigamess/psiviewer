package idea.plugin.psiviewer.controller.actions;

import cn.hutool.http.HttpUtil;

import java.util.Map;

public class open_pseudocode_IdaAction extends BaseIdaAction {
    @Override
    protected void handler(String ea) {
        String url = buildUrl("/open_pseudocode");
        HttpUtil.get(url, Map.of("ea", ea));
    }
}
