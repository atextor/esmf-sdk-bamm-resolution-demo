import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;

import org.eclipse.esmf.aspectmodel.resolver.AspectModelResolver;
import org.eclipse.esmf.aspectmodel.resolver.FileSystemStrategy;
import org.eclipse.esmf.aspectmodel.resolver.ResolutionStrategy;
import org.eclipse.esmf.aspectmodel.resolver.services.VersionedModel;
import org.eclipse.esmf.aspectmodel.urn.AspectModelUrn;

import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

public class LoadBammTest {
   @Test
   void testLoadSldtBammModel() {
      final String ASPECT_URN = "urn:bamm:io.catenax.part_as_planned:1.0.0#PartAsPlanned";
      final String BASE_DIR = "sldt-semantic-models";
      final ResolutionStrategy fileSystemStrategy = new FileSystemStrategy( Path.of( BASE_DIR ) );
      final Try<VersionedModel> tryModel = new AspectModelResolver().resolveAspectModel( fileSystemStrategy,
            AspectModelUrn.fromUrn( ASPECT_URN ) );

      assertTrue( tryModel.isSuccess() );
      assertFalse( tryModel.get().getRawModel().listStatements().toList().isEmpty() );
   }
}
