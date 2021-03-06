package org.jboss.da.communication.pnc.api;

import java.util.List;

import org.jboss.da.communication.pnc.model.BuildConfiguration;
import org.jboss.da.communication.pnc.model.BuildConfigurationCreate;
import org.jboss.da.communication.pnc.model.BuildConfigurationSet;
import org.jboss.da.communication.pnc.model.Product;
import org.jboss.da.communication.pnc.model.Project;

/**
 *
 * @author Honza Brázdil <jbrazdil@redhat.com>
 */
public interface PNCConnector {

    List<BuildConfigurationSet> getBuildConfigurationSets() throws Exception;

    List<BuildConfiguration> getBuildConfigurations() throws Exception;

    BuildConfiguration createBuildConfiguration(BuildConfigurationCreate bc) throws Exception;

    BuildConfigurationSet createBuildConfigurationSet(BuildConfigurationSet bcs) throws Exception;

    /**
     * Finds BuildConfigurationSet with specific product version id and build configurations ids from pnc 
     * 
     * @param productVersionId
     * @param buildConfigurationIds
     * @return BuildConfigurationSet or null if it is not found
     */
    BuildConfigurationSet findBuildConfigurationSet(int productVersionId,
            List<Integer> buildConfigurationIds);

    List<Product> getProducts() throws Exception;

    List<Project> getProjects() throws Exception;

}
