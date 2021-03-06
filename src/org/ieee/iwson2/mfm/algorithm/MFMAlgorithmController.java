package org.ieee.iwson2.mfm.algorithm;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.ieee.iwson2.mfm.model.networkblueprint.NetworkBluePrint;
import org.ieee.iwson2.mfm.model.networkblueprint.NetworkBluePrintImpl;
import org.ieee.iwson2.mfm.model.networkelements.Cell;
import org.ieee.iwson2.mfm.view.MFMBottomPanel;

/**
 * @author prem
 */
// TODO: Candidate for spring
public class MFMAlgorithmController {
    static MFMAlgorithmController mfmAlgorithmController = null;

    public synchronized static MFMAlgorithmController getMFMAlgorithmController() {
        if (null == mfmAlgorithmController) {
            mfmAlgorithmController = new MFMAlgorithmController();
        }
        return mfmAlgorithmController;
    }

    public void assignPCIs() {
        NetworkBluePrint networkBluePrint = NetworkBluePrintImpl.getInstance();
        List<Cell> cells = networkBluePrint.getCellsOfSites();
        if (null == cells) {
            logger.info("No cells to assign PCIs.");
        }
        for (Cell singleCell : cells) {
            int pciValue = (new Random().nextInt(503));
            singleCell.setPci(pciValue);
        }

        final MfmCellCollection cellController = new MfmCellCollection(cells);
        printCells(cellController);
        AlgorithmLogContainer algorithmLogContainer = new AlgorithmLogContainer();
        final MfmBasedAlgorithm algorithm = new MfmBasedAlgorithm(cellController, algorithmLogContainer);
        algorithm.execute();
        printCells(cellController);
        algorithmLogContainer.createXmlFile();
    }

    private static void printCells(final MfmCellCollection cellController) {
        final List<Cell> cells = cellController.getCells();
        for (final Cell cell : cells) {
            logger.info(cell);
        }
    }

    static Logger logger = Logger.getLogger(MFMBottomPanel.class.getName());
}
