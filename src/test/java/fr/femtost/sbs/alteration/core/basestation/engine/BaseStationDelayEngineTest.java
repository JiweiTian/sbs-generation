package fr.femtost.sbs.alteration.core.basestation.engine;

import fr.femtost.sbs.alteration.core.basestation.message.BaseStationMessageFull;
import org.junit.Test;

import java.io.File;

import static fr.femtost.sbs.alteration.core.basestation.BstHelper.*;
import static fr.femtost.sbs.alteration.core.scenario.ScenarioHelper.*;
import static fr.femtost.sbs.alteration.core.scenario.Parameter.CHARAC_TIMESTAMP;
import static org.junit.Assert.assertEquals;
import static org.powermock.reflect.Whitebox.invokeMethod;

public class BaseStationDelayEngineTest {

    @Test
    public void applyAction_delay_15s() throws Exception {
        final BaseStationDelayEngine engine = new BaseStationDelayEngine(
                recording(new File(""), 1519833870987L),
                delay(
                        timeWindow(0, 60000),
                        parameters(
                                bstTarget("39AC47"),
                                parameter(CHARAC_TIMESTAMP, "-15000"))));
        final BaseStationMessageFull msg = bstMessageFull("39AC47", 1519833870987L, latitude(50.2), longitude(0.25), altitude(12349),
                groundSpeed(341), verticalRate(4), alert(true), emergency(false), spi(true), onGround(false), track(12.1));
        final String result = invokeMethod(engine, "applyAction", msg);
        assertEquals(
                "MSG,0,0,0,39AC47,0,2018/02/28,16:04:15.987,2018/02/28,16:04:15.987,,12349,341,12.1,50.2,0.25,4,,1,0,1,0",
                result);
    }

    @Test
    public void applyAction_advance_15s() throws Exception {
        final BaseStationDelayEngine engine = new BaseStationDelayEngine(
                recording(new File(""), 1519833870987L),
                delay(
                        timeWindow(0, 60000),
                        parameters(
                                bstTarget("39AC47"),
                                parameter(CHARAC_TIMESTAMP, "15000"))));
        final BaseStationMessageFull msg = bstMessageFull("39AC47", 1519833870987L, latitude(50.2), longitude(0.25), altitude(12349),
                groundSpeed(341), verticalRate(4), alert(true), emergency(false), spi(true), onGround(false), track(12.1));
        final String result = invokeMethod(engine, "applyAction", msg);
        assertEquals(
                "MSG,0,0,0,39AC47,0,2018/02/28,16:04:45.987,2018/02/28,16:04:45.987,,12349,341,12.1,50.2,0.25,4,,1,0,1,0",
                result);
    }
}