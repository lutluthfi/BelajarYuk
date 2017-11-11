package com.lasbon.belajaryuk_library.data;

import com.lasbon.belajaryuk_library.model.Berita;
import com.lasbon.belajaryuk_library.model.Beritas;

import io.reactivex.Observable;

/**
 * Created by rama on 11/11/17.
 */

public interface IBeritaInteractor {
    Observable<Beritas> getAllBerita();

    Observable<Berita> getBeritaById(int id);
}
