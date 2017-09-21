package ua.nure.tur.web.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class RatingStarsTagHandler extends SimpleTagSupport {


    private static final double DECIMAL_PART_FOR_HALF_STAR = 0.3;

    private String rating;

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public void doTag() throws JspException, IOException {
        try {
            double ratingNumber = Double.parseDouble(rating);
            int fullStars = (int) ratingNumber;
            boolean halfStar = (ratingNumber - fullStars) > DECIMAL_PART_FOR_HALF_STAR;
            int emptyStars = 5 - (halfStar ? 1 : 0) - fullStars;
            JspWriter out = getJspContext().getOut();
            for (int i = 0; i < fullStars; i++) {
                out.print("<i class=\"fa fa-star\"></i>");
            }
            if (halfStar) {
                out.print("<i class=\"fa fa-star-half-o\"></i>");
            }
            for (int i = 0; i < emptyStars; i++) {
                out.print("<i class=\"fa fa-star-o\"></i>");
            }


        } catch (NumberFormatException e) {
            throw new SkipPageException("Wrong format of double: " + rating);
        }
    }
}
