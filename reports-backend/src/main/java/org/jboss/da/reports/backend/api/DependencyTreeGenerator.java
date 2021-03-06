package org.jboss.da.reports.backend.api;

import java.util.Optional;
import org.jboss.da.communication.CommunicationException;
import org.jboss.da.communication.aprox.model.GAVDependencyTree;
import org.jboss.da.communication.model.GAV;
import org.jboss.da.reports.api.SCMLocator;

/**
 *
 * @author Honza Brázdil <janinko.g@gmail.com>
 */
public interface DependencyTreeGenerator {

    public Optional<GAVDependencyTree> getDependencyTree(SCMLocator scml)
            throws CommunicationException;

    public Optional<GAVDependencyTree> getDependencyTree(GAV gav) throws CommunicationException;

}
