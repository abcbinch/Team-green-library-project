package com.library.propertyeditor;

import com.library.dto.admin._normal.AuthorDTO;

import java.beans.PropertyEditorSupport;

public class AuthorDTOPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName(text.trim());
        setValue(authorDTO);
    }
}

