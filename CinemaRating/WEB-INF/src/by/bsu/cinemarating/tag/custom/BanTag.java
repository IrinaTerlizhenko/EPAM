package by.bsu.cinemarating.tag.custom;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Created by User on 19.06.2016.
 */
@SuppressWarnings("serial")
public class BanTag extends TagSupport { // todo
    @Override
    public int doStartTag() throws JspException {
        if ((boolean) pageContext.getSession().getAttribute("banned") == false) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doAfterBody() throws JspTagException {
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
