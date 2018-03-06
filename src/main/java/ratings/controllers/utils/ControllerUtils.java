package ratings.controllers.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

public final class ControllerUtils {

    public static final String ERROR_VIEW_NAME = "errorView";

    private ControllerUtils() {}

    public static ModelAndView createModelAndView(Exception exception, HttpStatus status) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(ERROR_VIEW_NAME);
        modelAndView.addObject("httpStatus", status);
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
