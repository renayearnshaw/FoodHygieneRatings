package ratings.service;

import ratings.model.Establishment;

import java.util.List;

public interface EstablishmentsService {
    List<Establishment> getEstablishmentsInAuthority(long authorityId);
}
