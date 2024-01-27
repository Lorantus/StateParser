package org.rd.parser.parsing;

import org.rd.parser.QcmChoice;
import org.rd.parser.QcmIndicator;
import org.rd.parser.QcuIndicator;

import java.util.Optional;

public interface Row {
    boolean isQcmRow();

    boolean isQcmChoice();

    Optional<QcmChoice> toQcmChoice();

    Optional<QcmIndicator> toQcmIndicator(Context context);

    Optional<QcuIndicator> toQcuIndicator(Context context);
}
