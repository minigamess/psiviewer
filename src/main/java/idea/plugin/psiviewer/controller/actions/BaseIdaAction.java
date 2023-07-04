package idea.plugin.psiviewer.controller.actions;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.lang.mutable.MutableObj;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import idea.plugin.psiviewer.util.ActionEventUtil;
import org.jetbrains.annotations.NotNull;

public abstract class BaseIdaAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = ActionEventUtil.getEditor(e);
        if (editor == null)
            return;
        final PsiFile psiFile = ActionEventUtil.getPsiFile(e);
        if (psiFile == null)
            return;
        CaretModel caretModel = editor.getCaretModel();
        int offset = caretModel.getOffset();
        PsiElement psiElement = psiFile.findElementAt(offset);

        if (psiElement != null) {
            MutableObj<String> hexStr = MutableObj.of(null);
            ReUtil.findAll(PatternPool.get("0x[0-9A-Fa-f]+"), psiElement.getText(), matcher -> {
                if (matcher.start() < offset - psiElement.getTextOffset())
                    hexStr.set(matcher.group());
            });

            if (StrUtil.isNotEmpty(hexStr.get())) {
                handler(hexStr.get());
            }
        }
    }

    protected abstract void handler(String ea);

    protected String buildUrl(String uri) {
        return "http://127.0.0.1:432" + uri;
    }
}
