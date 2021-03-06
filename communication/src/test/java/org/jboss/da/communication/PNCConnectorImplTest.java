package org.jboss.da.communication;

import org.jboss.da.common.json.DAConfig;
import org.jboss.da.common.util.Configuration;
import org.jboss.da.common.util.ConfigurationParseException;
import org.jboss.da.communication.pnc.authentication.PNCAuthentication;
import org.jboss.da.communication.pnc.impl.PNCConnectorImpl;
import org.jboss.resteasy.client.ClientRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.MultivaluedMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PNCConnectorImplTest {

    @Mock
    Configuration configuration;

    @Mock
    PNCAuthentication pncAuthenticate;

    @InjectMocks
    PNCConnectorImpl pncConnectorImpl;

    DAConfig daConfig = new DAConfig();

    String token = "magic_token";

    @Before
    public void configureDaConfig() throws ConfigurationParseException {
        daConfig.setPncServer("http://test.me");

        // mock DAConfig
        when(configuration.getConfig()).thenReturn(daConfig);

        // mock the token generated by pncAuthenticate
        when(pncAuthenticate.authenticate()).thenReturn(token);
    }

    @Test
    public void shouldGenerateRightUriBasedOnPncServerAndEndpoint() throws Exception {

        ClientRequest req = pncConnectorImpl.getClient("matin");
        assertEquals(req.getUri(), "http://test.me/pnc-rest/rest/matin");
    }

    @Test
    public void shouldGenerateRightUriBasedOnPncServerAndEndpointAuthenticated() throws Exception {

        ClientRequest req = pncConnectorImpl.getAuthenticatedClient("gabriella");
        assertEquals(req.getUri(), "http://test.me/pnc-rest/rest/gabriella");
    }

    @Test
    public void shouldAddAuthenticationTokenToHeaderForAuthenticatedEndpoint() throws Exception {

        ClientRequest req = pncConnectorImpl.getAuthenticatedClient("testme");

        MultivaluedMap<String, String> headers = req.getHeaders();
        assertTrue(headers.containsKey("Authorization"));
        assertEquals(headers.getFirst("Authorization"), "Bearer " + token);
    }
}
