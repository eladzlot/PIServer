package edu.virginia.psyc.pi.persistence;

import edu.virginia.psyc.pi.domain.Participant;
import edu.virginia.psyc.pi.domain.Session;

import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: dan
 * Date: 6/24/14
 * Time: 1:30 PM
 * Allows us to inject a custom method into the automatically implemented
 * ParticipantRepository
 */
public class ParticipantRepositoryImpl implements ParticipantRepositoryCustom {

    @Override
    public Participant entityToDomain(ParticipantDAO dao) {
        Participant p = new Participant();
        List<Session> sessionList =  Session.createListView(dao.getCurrentSession(), dao.getTaskIndex());

        p.setId(dao.getId());
        p.setFullName(dao.getFullName());
        p.setEmail(dao.getEmail());
        p.setAdmin(dao.isAdmin());
        p.setSessions(sessionList);
        p.setTaskIndex(dao.getTaskIndex());

        return p;
    }

    @Override
    public void domainToEntity(Participant p, ParticipantDAO dao) {
        dao.setId(p.getId());
        dao.setFullName(p.getFullName());
        dao.setEmail(p.getEmail());
        dao.setAdmin(p.isAdmin());
        dao.setTaskIndex(p.getTaskIndex());
        dao.setCurrentSession(p.getCurrentSession().getName());
    }


}
