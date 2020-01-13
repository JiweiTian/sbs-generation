package fr.femtost.sbs.alteration.core.incident;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fr.femtost.sbs.alteration.core.utils.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IncidentDeserializer {

    private final File incidentFile;

    public IncidentDeserializer(final File incidentFile) {
        this.incidentFile = incidentFile;
    }

    public Scenario deserialize() throws IOException {
        final XmlMapper mapper = new XmlMapper();
        final String content = StreamUtils.inputStreamToString(new FileInputStream(incidentFile));
        return mapper.readValue(content, Scenario.class);
    }
}