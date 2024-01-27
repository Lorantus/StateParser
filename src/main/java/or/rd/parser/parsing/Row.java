package or.rd.parser.parsing;

import or.rd.parser.QcmChoice;
import or.rd.parser.QcmIndicator;
import or.rd.parser.QcuIndicator;

import java.util.Optional;

public interface Row {
    boolean isQCM();

    boolean isWithPeriod();

    Optional<QcmChoice> toQcmChoice();

    Optional<QcmIndicator> toQcmIndicator(Context context);

    Optional<QcuIndicator> toQcuIndicator(Context context);
}
