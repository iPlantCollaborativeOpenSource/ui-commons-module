package org.iplantc.core.uicommons.client.validators;

import java.util.List;

import org.iplantc.core.resources.client.messages.I18N;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class DoubleAboveValidator extends AbstractValidator<Double> {

    private final Double minNumber;

    public DoubleAboveValidator(Double minNumber) {
        this.minNumber = minNumber;
    }

    @Override
    public List<EditorError> validate(Editor<Double> editor, Double value) {
        if (value != null && (value <= minNumber)) {
            return createError(editor, I18N.VALIDATION.notAboveValueMsg("", minNumber), value);
        }

        return null;
    }

    public Double getMinNumber() {
        return minNumber;
    }
}