/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.sonar.server.computation.step;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.sonar.batch.protocol.output.ReportHelper;
import org.sonar.core.persistence.DbSession;
import org.sonar.server.computation.AnalysisReportService;
import org.sonar.server.computation.ComputeEngineContext;

import java.io.File;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CleanReportStepTest {

  @Rule
  public TemporaryFolder temp = new TemporaryFolder();

  CleanReportStep sut;

  @Test
  public void call_delete_directory() throws Exception {
    AnalysisReportService service = mock(AnalysisReportService.class);
    sut = new CleanReportStep(service);

    ComputeEngineContext context = mock(ComputeEngineContext.class);
    when(context.getReportHelper()).thenReturn(ReportHelper.create(temp.newFolder()));

    sut.execute(mock(DbSession.class), context);

    verify(service).deleteDirectory(any(File.class));
  }
}
