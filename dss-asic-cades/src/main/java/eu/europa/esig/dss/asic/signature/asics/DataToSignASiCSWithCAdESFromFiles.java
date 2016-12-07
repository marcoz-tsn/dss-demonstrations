package eu.europa.esig.dss.asic.signature.asics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import eu.europa.esig.dss.DSSDocument;
import eu.europa.esig.dss.asic.ASiCParameters;
import eu.europa.esig.dss.asic.GetDataToSignHelper;
import eu.europa.esig.dss.utils.Utils;

public class DataToSignASiCSWithCAdESFromFiles extends AbstractASiCSGetDataToSign implements GetDataToSignHelper {

	private final List<DSSDocument> filesToBeSigned;
	private final ASiCParameters asicParameters;

	private List<DSSDocument> signedDocuments;

	public DataToSignASiCSWithCAdESFromFiles(List<DSSDocument> filesToBeSigned, ASiCParameters asicParameters) {
		this.filesToBeSigned = filesToBeSigned;
		this.asicParameters = asicParameters;
	}

	@Override
	public String getSignatureFilename() {
		return getSignatureFileName(asicParameters);
	}

	@Override
	public DSSDocument getToBeSigned() {
		return getSignedDocuments().get(0);
	}

	@Override
	public List<DSSDocument> getDetachedContents() {
		return null;
	}

	@Override
	public DSSDocument getExistingSignature() {
		return null;
	}

	@Override
	public List<DSSDocument> getSignedDocuments() {
		if (signedDocuments == null) {
			if (Utils.collectionSize(filesToBeSigned) > 1) {
				signedDocuments = Arrays.asList(createPackageZip(filesToBeSigned));
			} else {
				signedDocuments = new ArrayList<DSSDocument>(filesToBeSigned);
			}
		}
		return signedDocuments;
	}

	@Override
	public List<DSSDocument> getManifestFiles() {
		// No manifest file in ASiC-S
		return Collections.emptyList();
	}

	@Override
	public List<DSSDocument> getSignatures() {
		// new container
		return new ArrayList<DSSDocument>();
	}

}
