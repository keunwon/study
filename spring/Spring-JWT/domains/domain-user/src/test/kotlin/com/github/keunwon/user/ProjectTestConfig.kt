package com.github.keunwon.user

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode

class ProjectTestConfig : AbstractProjectConfig() {
    override val isolationMode: IsolationMode = IsolationMode.InstancePerLeaf
}
